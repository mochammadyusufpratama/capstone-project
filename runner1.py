import sys
import os
import tensorflow as tf
import numpy as np
import cv2
from tensorflow.keras.models import load_model
import urllib.request 

# Menjalankan model training
script_directory = os.path.dirname(os.path.abspath(__file__))
model_path = os.path.join(script_directory, "model_35.h5")

imported_model = load_model(model_path)

#  Memproses input image
def preprocessing_img(path):
    orig = cv2.imread(path)
    orig = cv2.resize(orig, (224, 224))
    orig = orig / 255.0
    orig = np.expand_dims(orig, axis=0)
    return orig

# Periksa apakah jalur gambar disediakan sebagai argumen baris perintah
if len(sys.argv) !=2:
    print("Usage : python runner1.py <path>")
    sys.exit(1)

# Dapatkan jalur gambar dari argumen baris perintah

image_url = sys.argv[1]
# path = sys.argv[1]

try:
    urllib.request.urlretrieve(image_url, 'downloaded_image.jpg')
    path = 'downloaded_image.jpg'
except Exception as e:
    print("Error:", e)
    sys.exit(1)

# Membuat prediksi 
input_image = preprocessing_img(path)

# Menggunakan model terbaik
best_model = imported_model

predictions = best_model.predict(input_image)
labels = ['Organic', 'Recycled']

# Dengan asumsi prediksi adalah array yang dikodekan one-hot
predict_class = np.argmax(predictions)
confidence = predictions[0, predict_class]
predict_class_label = labels[predict_class]

# Cetak label kelas prediksi dan confidence
print(predict_class_label)
print(confidence)
