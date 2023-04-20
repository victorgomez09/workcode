from flask import Flask
from flask import jsonify
from flask_sqlalchemy import SQLAlchemy
from flask_bcrypt import Bcrypt
from flask_jwt_extended import JWTManager

app = Flask(__name__)
app.config.from_object('config')

flask_bcrypt = Bcrypt(app)
jwt = JWTManager(app)
db = SQLAlchemy(app)

with app.app_context():
    from app.auth.controllers import auth
    app.register_blueprint(auth)
    db.create_all()

@app.errorhandler(405)
def not_found_error():
    return jsonify({ 'ok': False, 'message': 'Method not allowed' }), 405


@app.errorhandler(404)
def not_found_error():
    return jsonify({ 'ok': False, 'message': 'Not Found' }), 404


@app.errorhandler(500)
def internal_error():
    db.session.rollback()
    return jsonify({ 'ok': False, 'message': 'Internal Server Error' }), 500

