"""User model"""
from app import db

class Base(db.Model):
    """Base model class"""
    __abstract__  = True
    id = db.Column(db.Integer, primary_key=True)
    date_created  = db.Column(db.DateTime, default=db.func.current_timestamp())
    date_modified = db.Column(db.DateTime, default=db.func.current_timestamp(),
                                           onupdate=db.func.current_timestamp())

class User(Base):
    """User model class"""
    __tablename__ = "users"
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255), unique=True, nullable=False)
    email = db.Column(db.String(255), unique=True, nullable=False)
    password = db.Column(db.String(255), unique=False, nullable=False)

    def __init__(self, name, password, email):
        self.name = name
        self.password = password
        self.email = email

    def json(self):
        """Serialize"""
        return {
            "id": self.id,
            "username": self.name,
            "email": self.email
        }

    def save_to_db(self):
        """Method to save user"""
        db.session.add(self)
        db.session.commit()

    def remove_from_db(self):
        """Method to delete user"""
        db.session.delete(self)
        db.session.commit()

    @classmethod
    def find_user_by_username(cls, username):
        return cls.query.filter_by(username=username).first()
