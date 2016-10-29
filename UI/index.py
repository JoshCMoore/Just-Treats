from flask import Flask
from flask import render_template
app = Flask(__name__)

@app.route('/')
def hello_world():
    return render_template('main.html')
@app.route('/add')
def add_location():
    return render_template('home.html')

if __name__ == '__main__':
    app.run()
