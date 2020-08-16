from django.contrib import admin
from django.urls import path,include
from .views import StudentView,InstructorView,CourseView,ModuleView,SubjectView,StudentupdateView,CourseupdateView,InstructorupdateView,SubjectupdateView,ModuleupdateView


urlpatterns=[
    path('student/',StudentView.as_view()),
    path('student/<int:pk>',StudentupdateView.as_view()),
    path('instructor/',InstructorView.as_view()),
    path('instructor/<int:pk>',InstructorupdateView.as_view()),
    path('module/',ModuleView.as_view()),
    path('module/<int:pk>', ModuleupdateView.as_view()),
    path('subject/',SubjectView.as_view()),
    path('subject/<int:pk>', SubjectupdateView.as_view()),
    path('course/',CourseView.as_view()),
    path('course/<int:pk>', CourseupdateView.as_view())
    ]
