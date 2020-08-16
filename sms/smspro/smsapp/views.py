from django.shortcuts import render

from .serializers import SMSSerializer
from .models import SMS
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response



# Create your views here.

@api_view(['GET', 'POST'])
def snippet_list(request):
  
    if request.method == 'GET':
        snippets = SMS.objects.all()
        serializer = SMSSerializer(snippets, many=True)
        return Response(serializer.data)

    elif request.method == 'POST':
        serializer = SMSSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
