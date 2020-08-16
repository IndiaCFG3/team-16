from .models import Student,Instructor,Subject,Course,Module
from rest_framework import serializers


class StudentEditSerializer(serializers.ModelSerializer):
    class Meta:
        model = Student
        exclude = ['ID']


class StudentListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Student
        fields = '__all__'


class InstructorEditSerializer(serializers.ModelSerializer):
    class Meta:
        model = Instructor
        exclude = ['ID']


class InstructorListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Instructor
        fields = '__all__'


class SubjectEditSerializer(serializers.ModelSerializer):
    class Meta:
        model = Subject
        exclude = []


class SubjectListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Subject
        fields = '__all__'

class ModuleEditSerializer(serializers.ModelSerializer):
    class Meta:
        model = Module
        exclude = []


class ModuleListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Module
        fields = '__all__'
class CourseEditSerializer(serializers.ModelSerializer):
    class Meta:
        model = Module
        exclude = []


class CourseListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Module
        fields = '__all__'




