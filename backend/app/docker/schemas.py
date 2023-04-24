"""Workspace schemas"""
from jsonschema import validate
from jsonschema.exceptions import ValidationError
from jsonschema.exceptions import SchemaError

workspace_schema = {
    "type": "object",
    "properties": {
        "name": {
            "type": "string",
        },
        "description": {
            "type": "string"
        },
        "color": {
            "type": "string"
        }
    },
    "required": ["name"],
    "additionalProperties": False
}


def validate_workspace(data):
    """Validate workspace with schema"""
    try:
        validate(data, workspace_schema)
    except ValidationError as error:
        return {'ok': False, 'message': error}
    except SchemaError as error:
        return {'ok': False, 'message': error}
    return {'ok': True, 'data': data}
