import mysql.connector
from mysql.connector import Error
 
 
def connect():
    """ Connect to MySQL database """
    try:
        conn = mysql.connector.connect(host='localhost',
                                       database='HCKNC',
                                       user='treats',
                                       password='HackNC2016')


        cursor = conn.cursor()

        lat = 0.0
        long = 0.0

        args = [lat, long]
        cursor.callproc('InsertLoc', args)
        if conn.is_connected():
            print('Connected to MySQL database')
 
    except Error as e:
        print(e)
 
    finally:
        conn.close()
 
 
if __name__ == '__main__':
    connect()
