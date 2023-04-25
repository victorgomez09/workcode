"""Workspace schemas"""
from jsonschema import validate
from jsonschema.exceptions import ValidationError
from jsonschema.exceptions import SchemaError

application_schema = {
    "type": "object",
    "properties": {
        "application_url": {
            "type": "string",
        }
    },
    "required": ["application_url"],
    "additionalProperties": False
}


def validate_application(data):
    """Validate application config with schema"""
    try:
        validate(data, application_schema)
    except ValidationError as error:
        return {'ok': False, 'message': error}
    except SchemaError as error:
        return {'ok': False, 'message': error}
    return {'ok': True, 'data': data}
