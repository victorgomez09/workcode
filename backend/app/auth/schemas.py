"""Auth/User schemas"""
from jsonschema import validate
from jsonschema.exceptions import ValidationError
from jsonschema.exceptions import SchemaError

user_schema = {
    "type": "object",
    "properties": {
        "name": {
            "type": "string",
        },
        "email": {
            "type": "string",
            "format": "email"
        },
        "password": {
            "type": "string",
            "minLength": 5
        }
    },
    "required": ["email", "password"],
    "additionalProperties": False
}


def validate_user(data):
    """Validate user with schema"""
    try:
        validate(data, user_schema)
    except ValidationError as error:
        return {'ok': False, 'message': error}
    except SchemaError as error:
        return {'ok': False, 'message': error}
    return {'ok': True, 'data': data}
