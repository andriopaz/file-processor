# Description

This is a project to test my skills as a developer.
The application was built using JDK 11 + Spring boot + H2 (memory database).
It reads all the .dat files in %HOMEPATH%/data/in and then it generates a report file in %HOMEPATH%/data/out folder.
A lot of Junit tests were created before developing the structure itself (TDD).
I could use only memory management instead of using a memory database, but I did rather use a DB.

# The generated report

The format file is .done.dat
It contains:
- The number of customers of the input file
- The number of salesmans of the input file
- The ID of most expensive sale 
- The worst salesman (criterias: number of sales, total value of sales he has done so far)

# How does it work?

There is a watcher running and it is capable of identify when a file is either created or modified.
The watcher triggers events to read all the input files and for each input file a new output file will be created.
If the input/output directories don't exists, they will be created automatically.

# If I could change something about the solution proposal, what would I change?

I would replace this layout:

003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

by that one:

003çSale IDç[Item ID-Item Quantity-Item Price]çCPF

The reason is quite simple, people might have the same name and the program wouldn't know which person the sale should be assigned to.

# How to build and run the application

1 - Navigate to the file-report-processor folder via cmd or powershell
2 - Generate a docker image through docker-compose:
	> docker-compose build
3 - Run this image through docker-compose:
	> docker-compose up
	
# Comments

- I could create a generic converter but it would increase code's complexity a lot due to SaleConverter.
So I decided to keep those converters in order to make it clean, maintainable and easy to read.
- Also there aren't too many classes/code because of strategy pattern. 