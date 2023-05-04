"""Class with docker routes"""
from flask import request, jsonify, Blueprint
from flask_jwt_extended import jwt_required
from flask_jwt_extended import get_jwt_identity

from app.docker.schemas import validate_workspace
from app.docker.engine import create_service, get_services_by_label
from app.application.models import Application

workspace = Blueprint('workspaces', __name__, url_prefix='/workspaces')


@workspace.route("/create", methods=['POST'])
@jwt_required()
def create_workspace():
    """Method to create a new workspace"""

    data = validate_workspace(request.get_json())
    if data['ok']:
        data = data['data']
        user = get_jwt_identity()
        service = create_service(data, user)

        return jsonify({'ok': True, 'data': service})

    return jsonify({'ok': False, 'message': f'Bad request parameters: {data["message"]}'}), 400


@workspace.route("/list")
@jwt_required()
def get_workspaces():
    """Route to get all user services"""

    application_config = Application.query.first().json()
    user = get_jwt_identity()
    services = get_services_by_label(
        user['name'], application_config['application_url'])
    if services is not None:
        return jsonify({'ok': True, 'data': services})

    return jsonify({'ok': False, 'message': 'Something goes wrong'}), 500
