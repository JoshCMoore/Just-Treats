from flask import Flask
from flask import request
from flask import render_template
import mysql.connector
from mysql.connector import Error
app = Flask(__name__)

@app.route('/')
def hello_world():
    return render_template('map.html')
@app.route('/add')
def add_location():
    return render_template('home.html')

@app.route('/coord')
def api_coord():
    if 'lat' in request.args:
	    lat =  request.args['lat']
    if 'long' in request.args:
	    long = request.args['long']

    print(lat + " : " + long)

    try:
        conn = mysql.connector.connect(host='localhost',
                                       database='HCKNC',
                                       user='treats',
                                       password='HackNC2016')


        cursor = conn.cursor()

        try:
            cursor.execute("""INSERT INTO treats VALUES (%s,%s)""",(lat,long))
            conn.commit()
        except:
            conn.rollback()

    finally:
        cursor.close()
        conn.close()



    	
    return "okay"




if __name__ == '__main__':
    app.run(host='0.0.0.0', port=80)
