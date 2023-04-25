"""Application config model"""
from app import db

class Base(db.Model):
    """Base model class"""
    __abstract__  = True
    id = db.Column(db.Integer, primary_key=True)
    date_created  = db.Column(db.DateTime, default=db.func.current_timestamp())
    date_modified = db.Column(db.DateTime, default=db.func.current_timestamp(),
                                           onupdate=db.func.current_timestamp())

class Application(Base):
    """Application model class"""
    __tablename__ = "application_config"
    application_url = db.Column(db.String(255), unique=True, nullable=False)

    def __init__(self, application_url):
        self.application_url = application_url

    def json(self):
        """Serialize"""
        return {
            "id": self.id,
            "application_url": self.application_url
        }

    def save_to_db(self):
        """Method to save application"""
        db.session.add(self)
        db.session.commit()

    def remove_from_db(self):
        """Method to delete application"""
        db.session.delete(self)
        db.session.commit()
