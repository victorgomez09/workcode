"""Application entrypoint"""
from flask import Flask
from flask import jsonify
from flask_sqlalchemy import SQLAlchemy
from flask_bcrypt import Bcrypt
from flask_jwt_extended import JWTManager
from flask_cors import CORS

app = Flask(__name__)
app.config.from_object('config')

flask_bcrypt = Bcrypt(app)
jwt = JWTManager(app)
db = SQLAlchemy(app)
cors = CORS(app, resources={
            r"/*": {"origins": "https://victorgomez09-ominous-chainsaw-vg7x6gjv46536p5p-3000.preview.app.github.dev/"}})

with app.app_context():
    from app.auth.controllers import auth
    app.register_blueprint(auth)
    db.create_all()

    from app.docker.controllers import workspace
    app.register_blueprint(workspace)

    from app.application.controllers import application
    app.register_blueprint(application)

    from app.docker.engine import image_pull, init_docker
    swarmToken = init_docker()
    print(swarmToken)
    image_pull('linuxserver/code-server')

    db.create_all()


@app.errorhandler(405)
def method_not_allowed():
    """Mthod to return a 405 code"""
    return jsonify({'ok': False, 'message': 'Method not allowed'}), 405


@app.errorhandler(404)
def not_found_error():
    """Method to return a 404"""
    return jsonify({'ok': False, 'message': 'Not Found'}), 404


@app.errorhandler(500)
def internal_error():
    """Method to return a 500 code"""
    db.session.rollback()
    return jsonify({'ok': False, 'message': 'Internal Server Error'}), 500
