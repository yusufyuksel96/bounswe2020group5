from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import *
from django.views.generic import TemplateView

router = DefaultRouter()
router.register(r'users', UserViewSet, basename='users')
router.register(r'auth', AuthViewSet, basename='auth')

urlpatterns = [
    path('', include(router.urls)),
    
    # Route TemplateView to serve Swagger UI template.
    path('docs/', TemplateView.as_view(
        template_name='swagger-ui.html',
        extra_context={'schema_url':'openapi-schema'}
    ), name='swagger-ui'),
]