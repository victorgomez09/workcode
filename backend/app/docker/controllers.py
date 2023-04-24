"""Class with docker routes"""
from flask import request, jsonify, Blueprint
from flask_jwt_extended import jwt_required
from flask_jwt_extended import get_jwt_identity

from app.docker.schemas import validate_workspace
from app.docker.engine import create_service

workspace = Blueprint('workspaces', __name__, url_prefix='/workspaces')


@workspace.route("/create-workspace", methods=['POST'])
@jwt_required()
def create_workspace():
    """Method to create a new workspace"""
    data = validate_workspace(request.get_json())

    if data['ok']:
        data = data['data']
        user = get_jwt_identity()
        service = create_service(data, user)

        return jsonify({'ok': True, 'data': service})
        
    return jsonify({'ok': False, 'message': 'Bad request parameters: {}'.format(data['message'])}), 400
