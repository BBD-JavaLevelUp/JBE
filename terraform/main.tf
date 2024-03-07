# terraform {
#   required_providers {
#     aws = {
#       source = "hashicorp/aws"
#       version = "5.39.1"
#     }
#   }

#   required_version = "~> 1.7.3" //Terraform version
# }

provider "aws" {
  region = "eu-west-1"
}

data "aws_availability_zones" "available" {
  state = "available"
}

//VPC
resource "aws_vpc" "jbe_vpc" {
  cidr_block = var.vpc_cidr_block
  enable_dns_hostnames = true
  
  tags = {
    Name = "jbe_vpc"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

//Internet gateway
resource "aws_internet_gateway" "jbe_igw" {
  vpc_id = aws_vpc.jbe_vpc.id
  
  tags = {
    Name = "jbe_igw"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

//Public subnet
resource "aws_subnet" "jbe_public_subnet" {
  count = var.subnet_count.public
  vpc_id = aws_vpc.jbe_vpc.id
  cidr_block = var.public_subnet_cidr_blocks[count.index]
  availability_zone = data.aws_availability_zones.available.names[count.index]
  
  tags = {
    Name = "jbe_public_subnet_${count.index}"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

//Private subnets
resource "aws_subnet" "jbe_private_subnet" {
  count = var.subnet_count.private
  vpc_id = aws_vpc.jbe_vpc.id
  cidr_block = var.private_subnet_cidr_blocks[count.index]
  availability_zone = data.aws_availability_zones.available.names[count.index]
  
  tags = {
    Name = "jbe_private_subnet_${count.index}"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

//Route tables - Public
resource "aws_route_table" "jbe_public_rt" {
  vpc_id = aws_vpc.jbe_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.jbe_igw.id
  }
  
  tags = {
    Name = "jbe_public_rt"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

resource "aws_route_table_association" "public" {
  count = var.subnet_count.public
  route_table_id = aws_route_table.jbe_public_rt.id
  subnet_id = aws_subnet.jbe_public_subnet[count.index].id
}

//Route tables - Private
resource "aws_route_table" "jbe_private_rt" {
  vpc_id = aws_vpc.jbe_vpc.id
  
  tags = {
    Name = "jbe_private_rt"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

resource "aws_route_table_association" "private" {
  count = var.subnet_count.private
  route_table_id = aws_route_table.jbe_private_rt.id
  subnet_id = aws_subnet.jbe_private_subnet[count.index].id
}

//EC2 Security group
resource "aws_security_group" "jbe_web_sg" {
  name = "jbe_web_sg"
  description = "Security group for JBE web server"
  vpc_id = aws_vpc.jbe_vpc.id

  ingress {
    description = "Allow all traffic through HTTP"
    from_port = "8080"
    to_port = "8080"
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Allow SSH from my computer"
    from_port = "22"
    to_port = "22"
    protocol = "tcp"
    cidr_blocks = ["${var.my_ip}/32"]
  }

  egress {
    description = "Allow all outbound traffic"
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "jbe_web_sg"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

//RDS Security Group
resource "aws_security_group" "jbe_db_sg" {
  name = "jbe_db_sg"
  description = "Security group for JBE database"
  vpc_id = aws_vpc.jbe_vpc.id

  ingress {
    description = "Allow PostgreSQL traffic from only the web sg"
    from_port = "5432"
    to_port = "5432"
    protocol = "tcp"
    security_groups = [aws_security_group.jbe_web_sg.id]
  }

  tags = {
    Name = "jbe_db_sg"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

//Db Subnet Group
resource "aws_db_subnet_group" "jbe_db_subnet_group" {
  name = "jbe_db_subnet_group"
  description = "DB subnet group for JBE"
  subnet_ids = [for subnet in aws_subnet.jbe_private_subnet : subnet.id]

  tags = {
    Name = "jbe_db_sng"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

//PostgreSQL database
resource "aws_db_instance" "jbe_database" {
  identifier = "jbe-database"
  allocated_storage = var.settings.database.allocated_storage
  engine = var.settings.database.engine
  engine_version = var.settings.database.engine_version
  instance_class = var.settings.database.instance_class
  db_name = var.settings.database.db_name
  username = var.db_username
  password = var.db_password
  db_subnet_group_name = aws_db_subnet_group.jbe_db_subnet_group.id
  vpc_security_group_ids = [aws_security_group.jbe_db_sg.id]
  skip_final_snapshot = var.settings.database.skip_final_snapshot

  tags = {
    Name = "jbe_database"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

resource "aws_key_pair" "jbe_kp" {
  key_name = "jbe_kp"
  public_key = var.public_key

  tags = {
    name = "jbe_kp"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

data "aws_ami" "ubuntu" {
  most_recent = "true"

  filter {
    name = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-jammy-22.04-amd64-server-*"]
  }

  filter {
    name = "virtualization-type"
    values = ["hvm"]
  }

  owners = ["099720109477"]
}

resource "aws_instance" "jbe_web" {
  count = var.settings.web_app.count
  ami = data.aws_ami.ubuntu.id
  instance_type = var.settings.web_app.instance_type
  subnet_id = aws_subnet.jbe_public_subnet[count.index].id
  key_name = aws_key_pair.jbe_kp.key_name
  vpc_security_group_ids = [aws_security_group.jbe_web_sg.id]

  tags = {
    Name = "jbe_web"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}

resource "aws_eip" "jbe_web_eip" {
  count = var.settings.web_app.count
  instance = aws_instance.jbe_web[count.index].id

  tags = {
    Name = "jbe_web_eip_${count.index}"
    owner = "thashil.naidoo@bbd.co.za"
    created-using = "terraform"
  }
}