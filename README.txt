
-Open sql file with sqldeveloper
-Run the first two lines as admin
-Connect to the created user (USER: HIBERNATE_FINAL, PASSW: HIBERNATE_FINAL)
-Run the rest of the script as HIBERNATE_USER
-Now you can run HibernateFinal.jar, located in the root directory.

How does it work:

		Products:

			-This tab shows us all products we have in our DB.
			-Here we can:
				+Add article. A window will open. There we will be able to add an article we define.
				+Delete article. We can select a row from the table and remove it with this button.
				+Edit article. The article we selected from the table with the fields on the bottom.
				+Search. We can search any type of value in the table (Reference, description, value, etc...)

		Customers:

			-This tab shows us all customers we have in our DB. 
			-Here we can: 
				+Add customer. A window will open where we can create a customer.
				+Edit or remove customer. When selecting a row, we can choose to remove or edit it.
				+Create statistic. A window will open where we can write the range we want for customers and date and a statistic will be created for every client every month.
				+Search. We can search for name or DNI in the table.

		Bills:

			-This tab shows us all customers we have in our DB.
			-Here we can:
				+New bill. It opens a windows where we can fill the number of the bill and the date. When accepting we can add lines choosing the product, the amount and the discount in % we want for it.
				 	· We also can create an offer. This will export our actual bill to XML, and we're able to load that offer later, by pressing "Load offer".
				+If we select a bill from the table we can:
					·Export to xml and json. A file will be created where we choose in xml or json format.
					·Remove bill. The bill will be removed, and the stock will be added to the products.

		Statistics:

			- Shows us the statistics we created.