#!/usr/bin/env python3
from flask import Flask
from flask_restful import Api
import markdown
from User import Users, UserList, fill_start

app = Flask(__name__)
api = Api(app)


@app.route('/')
def index():
    """Api documentation"""
    fill_start()
    with open('README.md', 'r') as markdown_file:
        # Read file and convert it to HTML
        content = markdown_file.read()

        return markdown.markdown(content)


api.add_resource(UserList, '/users')
api.add_resource(Users, '/users/<string:email>')


if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)
