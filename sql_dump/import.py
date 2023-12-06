import subprocess
import os

def import_sql(username, db_name, sql_file_path="/portfolio_management.sql"):
    try:
        
        command = [
            'psql',
            '-U', username,
            '-d', db_name,
            '-h', 'localhost',  
            '-f', os.path.dirname(os.path.abspath(__file__)) + sql_file_path
        ]

        
        subprocess.run(command, check=True)

        print("SQL file imported successfully.")
        
    except subprocess.CalledProcessError as e:
        print(f"Error importing SQL file: {e}")


username = input("Enter PostgreSQL username: ")
db_name = input("Enter database name: ")


import_sql(username, db_name)
