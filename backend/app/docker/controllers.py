"""Class with docker routes"""
from flask import request, jsonify, Blueprint
from flask_jwt_extended import jwt_required

from app.docker.schemas import validate_workspace
from app.docker.engine import create_service

workspace = Blueprint('workspaces', __name__, url_prefix='/workspaces')


@workspace.route("/create-workspace", methods=['POST'])
@jwt_required()
def create_workspace():
    """Method to create a new workspace"""
    print(request.get_json())
    data = validate_workspace(request.get_json())

    if data['ok']:
        data = data['data']
        print('data',data)
        create_service(data)
        print('name', data['name'])

        return jsonify(request.get_json())
        
    return jsonify({'ok': False, 'message': 'Bad request parameters: {}'.format(data['message'])}), 400
