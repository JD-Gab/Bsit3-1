from flask import Flask, render_template, request
from flaskext.mysql import MySQL


app = Flask(__name__)
mysql = MySQL()

app.config['MYSQL_DATABASE_USER'] = "root"
app.config['MYSQL_DATABASE_PASSWORD'] = ""
app.config['MYSQL_DATABASE_DB'] = "bookdb"
app.config['MYSQL_DATABASE_HOST'] = "localhost"
mysql.init_app(app)

conn = mysql.connect()
cursor = conn.cursor()



@app.route('/')
def homepage():
    return render_template('homepage.html')


#function for searching items in the database
def search_items(search_term):
    query = 'SELECT * FROM booklist WHERE bookname LIKE %s OR authorname LIKE %s'
    cursor.execute(query, ('%' + search_term + '%', '%' + search_term + '%'))
    items = cursor.fetchall()
    conn.commit()
    return items


@app.route('/about', methods = ['GET', 'POST'])
def about():

    cursor.execute('SELECT * FROM booklist')
    database_info = cursor.fetchall()

    if request.method == 'POST':
        search_term = request.form['search']
        items = search_items(search_term)
        return render_template('about.html', data1=database_info, items=items)

    else:
        items = []

    return render_template('about.html', data1=database_info)


@app.route('/add', methods=['POST'])
def request_form():

    aname = request.form['author']
    cname = request.form['bookName']
    cursor.execute('INSERT INTO booklist (bookname, authorname) VALUES (%s, %s)', (cname, aname))
    conn.commit()
    return render_template('insertComplete.html')

@app.route('/updatetitle', methods=['GET','POST'])
def update_title():
    updatedTitle = request.form['changeBName']
    getID = request.form['idnum1']
    aname = request.form['author_name1']

    to_update = 'UPDATE booklist SET bookname = %s WHERE id = %s'

    cursor.execute(to_update, (updatedTitle, getID))
    conn.commit()
    return render_template('updateComplete.html')

@app.route('/updateauthor', methods=['GET','POST'])
def update_author():
    updatedAuthor = request.form['changeAName']
    getID = request.form['idnum1']
    bname = request.form['book_name1']

    to_update = 'UPDATE booklist SET authorname = %s WHERE id = %s'

    cursor.execute(to_update, (updatedAuthor, getID))
    conn.commit()
    return render_template('updateComplete.html')

@app.route('/delete', methods=['GET','POST'])
def delete_book():
    bookToDel = request.form['delBook']
    getID = request.form['idnum1']
    x = 0

    query = 'DELETE FROM booklist WHERE bookname = %s AND id = %s'
    cursor.execute(query, (bookToDel, getID))
    conn.commit()
    return render_template('deleteComplete.html')




if __name__ == "__main__":
    app.run(debug=True)

