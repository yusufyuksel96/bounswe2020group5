from django.db import models
from .users import Vendor, Customer
import json

class Document(models.Model):
    uploaded_at = models.DateTimeField(auto_now_add=True)
    upload = models.FileField()

class Category(models.Model):
    name = models.CharField(max_length=250)

    def __str__(self):
        return self.name

class SubCategory(models.Model):
    name = models.CharField(max_length=250)
    category = models.ForeignKey(Category, on_delete=models.CASCADE)

    def __str__(self):
        return self.name

class Comment(models.Model):
    user = models.ForeignKey(Customer, on_delete=models.CASCADE)
    comment_text = models.CharField(max_length=250)
    is_anonymous = models.BooleanField(default=False)

class Product(models.Model):
    name = models.CharField(max_length=250)
    price = models.DecimalField(max_digits=10, decimal_places=2)
    stock = models.IntegerField()
    description = models.TextField()
    date_added = models.DateTimeField(auto_now_add=True)
    number_of_sales = models.IntegerField(default=0)
    image_url = models.TextField(default='')
    #category = models.ForeignKey(Category, on_delete=models.CASCADE) 
    subcategory = models.ForeignKey(SubCategory, on_delete=models.CASCADE)
    vendor = models.ForeignKey(Vendor, on_delete=models.CASCADE)
    total_rating_score = models.IntegerField(default=0)
    rating_count = models.IntegerField(default=0)
    comments = models.ManyToManyField(Comment)  

    def __str__(self):
        return self.name
        
class ProductList(models.Model):
    name = models.CharField(max_length=250)
    user = models.ForeignKey(Customer, on_delete=models.CASCADE)
    products = models.ManyToManyField(Product)
    
    def __str__(self):
        return self.name

