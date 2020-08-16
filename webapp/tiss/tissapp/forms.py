from django import forms
from .models import Module,Instructor,Course,Subject

class SubjectForm(forms.ModelForm):
    class Meta():
        model=Subject
        fields=('title','slug')
        widgets={
            'title':forms.TextInput(attrs={'class':'textinputclass'}),
            'slug':forms.Textarea(attrs={'class': 'editable medium-editor-textarea postcontent'})

        }
class InstructorForm(forms.ModelForm):

    class Meta():
        model=Instructor
        fields=('title','mobilenumber')
        

        
class CourseForm(forms.ModelForm):

    class Meta():
        model=Course
        fields=('author','text')
        widgets={
            'author':forms.TextInput(attrs={'class':'textinputclass'}),
            'text':forms.Textarea(attrs={'class': 'editable medium-editor-textarea'})
        }
class StudentForm(forms.ModelForm):

    class Meta():
        model=Student
        fields=('ID','mobilenumber')
        
