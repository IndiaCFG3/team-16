from django.db import models

# Create your models here.
class SMS(models.Model):
    data=models.CharField(max_length=1000)
    num=models.IntegerField(max_length=256)
    