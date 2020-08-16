from django.contrib import admin
from .models import Module,Instructor,Course,Subject,Student
# Register your models here.
admin.site.register(Course)
admin.site.register(Subject)
admin.site.register(Student)
admin.site.register(Module)
admin.site.register(Instructor)

