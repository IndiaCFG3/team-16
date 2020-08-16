from .models import SMS
from rest_framework import serializers

class SMSSerializer(serializers.ModelSerializer):
    class Meta:
        model = SMS
        exclude = []
