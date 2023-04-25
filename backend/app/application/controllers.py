"""Class with docker routes"""
from flask import request, jsonify, Blueprint
from flask_jwt_extended import jwt_required

from app.application.models import Application
from app.application.schemas import validate_application

application = Blueprint('application', __name__, url_prefix='/application')

@application.route("/create", methods=['POST'])
@jwt_required()
def create_application():
    """Method to create the application config"""
    
    data = validate_application(request.get_json())
    if data['ok']:
        data = data['data']
        application_config = Application(application_url=data['application_url'])
        application_config.save_to_db()

        return jsonify({'ok': True, 'message': 'User created successfully'}), 200

    return jsonify({'ok': False, 'message': f'Bad request parameters: {data["message"]}'}), 400
