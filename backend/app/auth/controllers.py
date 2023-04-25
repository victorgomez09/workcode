"""Class with user and auth routes"""
from flask import request, jsonify, Blueprint
from flask_jwt_extended import create_access_token
from flask_jwt_extended import create_refresh_token
from flask_jwt_extended import jwt_required
from flask_jwt_extended import get_jwt_identity

from app import jwt, db, flask_bcrypt
from app.auth.models import User
from app.auth.schemas import validate_user

auth = Blueprint('auth', __name__)  # , url_prefix='/')


@auth.route('/', methods=['GET'])
def index():
    """Index route"""

    return jsonify({'ok': True, 'message': 'pong'}), 401


@jwt.unauthorized_loader
def unauthorized_response():
    """Default response when no auth is provided"""

    return jsonify({'ok': False, 'message': 'Missing authorization header'}), 401


@auth.route('/login', methods=['POST'])
def login():
    """Route to generate token with user info"""

    data = validate_user(request.get_json())
    if data['ok']:
        data = data['data']
        user = User.query.filter_by(email=data['email']).first()

        if user and flask_bcrypt.check_password_hash(user.password, data['password']):
            del user.password
            access_token = create_access_token(identity=user.json())
            refresh_token = create_refresh_token(identity=user.json())
            user.token = access_token
            user.refresh = refresh_token
            return jsonify({'ok': True, 'access_token': access_token, 'refresh_token': refresh_token, 'user': user.json()}), 200

        return jsonify({'ok': False, 'message': 'Invalid credentials'}), 401

    return jsonify({'ok': False, 'message': f'Bad request parameters: {data["message"]}'}), 400


@auth.route('/register', methods=['POST'])
def register():
    """Route to create new user"""

    data = validate_user(request.get_json())
    if data['ok']:
        data = data['data']
        data['password'] = flask_bcrypt.generate_password_hash(
            data['password']).decode('utf-8')
        user = User(name=data['name'], email=data['email'],
                    password=data['password'])
        db.session.add(user)
        db.session.commit()
        return jsonify({'ok': True, 'message': 'User created successfully'}), 200

    return jsonify({'ok': False, 'message': f'Bad request parameters: {data["message"]}'}), 400


@auth.route('/refresh', methods=['POST'])
@jwt_required(refresh=True)
def refresh():
    """Route to refresh token"""

    current_user = get_jwt_identity()
    ret = {'token': create_access_token(identity=current_user)}
    return jsonify({'ok': True, 'data': ret}), 200


@auth.route('/user/<user_id>', methods=['GET', 'DELETE', 'PATCH'])
@jwt_required()
def manage_user(user_id):
    """Route to manage user"""

    if request.method == 'GET':
        data = User.query.filter_by(id=user_id).first()
        return jsonify({'ok': True, 'data': data.json()}), 200

    # if request.method == 'DELETE':
    #    user = User.query.filter_by(id=user_id).first()
    #    User.remove_from_db(user)
    #    print('db_response', db_response)
    #    if db_response.deleted_count == 1:
    #        response = {'ok': True, 'message': 'record deleted'}
    #    else:
    #        response = {'ok': True, 'message': 'no record found'}
    #    return jsonify(response), 200

    if request.method == 'PATCH':
        if data.get('query', {}) != {}:
            db.users.update_one(
                data['query'], {'$set': data.get('payload', {})})
            return jsonify({'ok': True, 'message': 'record updated'}), 200

        return jsonify({'ok': False, 'message': 'Bad request parameters!'}), 400


@auth.route('/me', methods=['POST'])
@jwt_required()
def me():
    """method to get current user"""
    user = get_jwt_identity()
    return jsonify({'ok': True, 'data': user})
