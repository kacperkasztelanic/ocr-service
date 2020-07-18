# OcrService
A Tess4j-based OCR REST service.
A cloud-ready microservice allowing to perform an OCR process over uploaded images.  
It makes use of RabbitMQ as task queue for asynchronous processing, MongoDB for storing tasks, results and MinIO file store for images.  
Features: SpringBoot, RabbitMQ, MongoDB, MinIO, Vavr, Lombok, Maven, Docker, Functional Programming
