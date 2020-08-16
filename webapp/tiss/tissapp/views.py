from rest_framework.generics import ListCreateAPIView,GenericAPIView,RetrieveUpdateDestroyAPIView
from .models import Student,Instructor,Course,Subject,Module
from rest_framework.mixins import ListModelMixin,CreateModelMixin
from .serializers import StudentEditSerializer,StudentListSerializer,SubjectEditSerializer,SubjectListSerializer,ModuleEditSerializer,ModuleListSerializer,CourseEditSerializer,CourseListSerializer,InstructorEditSerializer,InstructorListSerializer
class StudentView(GenericAPIView,CreateModelMixin,ListModelMixin):
    queryset = Student.objects.all()
    serializer_class = StudentListSerializer

    def perform_create(self, serializer):
        return serializer.save()

    def get(self, request, *args, **kwargs):
        return self.list(request, *args, *kwargs)

    def post(self, request, *args, **kwargs):
        return self.create(request, *args, **kwargs)

        
class StudentupdateView(RetrieveUpdateDestroyAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentListSerializer

class SubjectView(GenericAPIView,CreateModelMixin,ListModelMixin):
    queryset = Subject.objects.all()
    serializer_class = SubjectListSerializer

    def perform_create(self, serializer):
        return serializer.save()

    def get(self, request, *args, **kwargs):
        return self.list(request, *args, *kwargs)

    def post(self, request, *args, **kwargs):
        return self.create(request, *args, **kwargs)

        
class SubjectupdateView(RetrieveUpdateDestroyAPIView):
    queryset = Subject.objects.all()
    serializer_class = SubjectListSerializer

class ModuleView(GenericAPIView,CreateModelMixin,ListModelMixin):
    queryset = Module.objects.all()
    serializer_class = ModuleListSerializer

    def perform_create(self, serializer):
        return serializer.save()

    def get(self, request, *args, **kwargs):
        return self.list(request, *args, *kwargs)

    def post(self, request, *args, **kwargs):
        return self.create(request, *args, **kwargs)

        
class ModuleupdateView(RetrieveUpdateDestroyAPIView):
    queryset = Module.objects.all()
    serializer_class = ModuleListSerializer

class InstructorView(GenericAPIView,CreateModelMixin,ListModelMixin):
    queryset = Instructor.objects.all()
    serializer_class = InstructorListSerializer

    def perform_create(self, serializer):
        return serializer.save()

    def get(self, request, *args, **kwargs):
        return self.list(request, *args, *kwargs)

    def post(self, request, *args, **kwargs):
        return self.create(request, *args, **kwargs)

        
class InstructorupdateView(RetrieveUpdateDestroyAPIView):
    queryset = Instructor.objects.all()
    serializer_class = InstructorListSerializer

class CourseView(GenericAPIView,CreateModelMixin,ListModelMixin):
    queryset = Course.objects.all()
    serializer_class = CourseListSerializer

    def perform_create(self, serializer):
        return serializer.save()

    def get(self, request, *args, **kwargs):
        return self.list(request, *args, *kwargs)

    def post(self, request, *args, **kwargs):
        return self.create(request, *args, **kwargs)

        
class CourseupdateView(RetrieveUpdateDestroyAPIView):
    queryset = Course.objects.all()
    serializer_class = CourseListSerializer