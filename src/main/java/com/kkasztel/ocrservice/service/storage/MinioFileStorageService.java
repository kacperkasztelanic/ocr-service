package com.kkasztel.ocrservice.service.storage;

import com.kkasztel.ocrservice.exception.FileStorageException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.vavr.control.Either;
import io.vavr.control.Option;

import static io.vavr.API.Try;

@Service
class MinioFileStorageService implements FileStorageService {

    private final MinioClient minioClient;

    @Autowired
    public MinioFileStorageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public Either<FileStorageException, byte[]> store(byte[] file, String name, String directory) {
        return Try(() -> {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(directory).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(directory).build());
            }
            minioClient.putObject(//
                    PutObjectArgs.builder()//
                            .bucket(directory)//
                            .object(name)//
                            .stream(new ByteArrayInputStream(file), file.length, -1)//
                            .build()//
            );
            return file;
        }).toEither().mapLeft(FileStorageException::new);
    }

    @Override
    public Either<FileStorageException, Option<byte[]>> load(String name, String directory) {
        return Try(() -> {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(directory).build())) {
                throw new FileStorageException("Requested bucket does not exist: " + directory);
            }
            return IOUtils.toByteArray(//
                    minioClient.getObject(//
                            GetObjectArgs.builder()//
                                    .bucket(directory)//
                                    .object(name)//
                                    .build()//
                    )//
            );
        }).map(Option::of).toEither().mapLeft(FileStorageException::new);
    }
}
