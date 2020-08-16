from django.db import models

# Create your models here.
class Instructor(models.Model):
    ID = models.AutoField(primary_key=True)
    title = models.ForeignKey('auth.User',related_name='instructor',on_delete=models.CASCADE)
    mobilenumber=models.PositiveIntegerField(max_length=12)
    
    def __str__(self):
        return self.title
class Student(models.Model):
    ID = models.AutoField(primary_key=True)
    title = models.ForeignKey('auth.User',related_name='student',on_delete=models.CASCADE)
    mobilenumber=models.PositiveIntegerField(max_length=12)
    
    def __str__(self):
        return self.title
class Subject(models.Model):
    title = models.CharField(max_length=200)
    slug = models.SlugField(max_length=200, unique=True)
    class Meta:
        ordering = ('title', )

    def __str__(self):
        return self.title


class Course(models.Model):
    owner = models.ForeignKey(Instructor, related_name='courses_created',on_delete=models.DO_NOTHING)
    subject = models.ForeignKey(Subject, related_name='courses',on_delete=models.DO_NOTHING)
    title = models.CharField(max_length=200)
    slug = models.SlugField(max_length=200, unique=True)
    overview = models.TextField()
    created = models.DateTimeField(auto_now=True)
    class Meta:
        ordering = ('-created',)

    def __str__(self):
        return self.title


class Module(models.Model):
    course = models.ForeignKey(Course, related_name='modules',on_delete=models.DO_NOTHING)
    title = models.CharField(max_length=200)
    description = models.TextField(blank=True)
    class Meta:
        ordering = ['title']

    def __str__(self):
        return '{}. {}'.format(self.order, self.title)


