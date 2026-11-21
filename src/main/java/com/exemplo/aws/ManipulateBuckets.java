package main.java.com.exemplo.aws;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.util.List; 

public class ManipulateBuckets {

    public static void main(String[] args){
        
        // Define a região onde o bucket será criado
        Region region = Region.US_EAST_1;

        // Cria um cliente S3 usando as credenciais padrão e a região especificada
        try (S3Cliente s3 = S3Client.builder().region(region).credentialsProvider(DefaultCredentialsProvider.create()).build()){

            // Lista todos os buckets na conta
            ListBucketsResponse response = s3.listBuckets(); 
            
            // Cria uma lista de buckets a partir da resposta
            List<Bucket> buckets = response.buckets();

            // Exibe o total de buckets encontrados
            System.out.println("Total de buckets encontrados: " + buckets.size()); 

            // Exibe o nome e a data de criação de cada bucket
            for (Bucket bucket: buckets){
                System.out.println("- Name: " + bucket.name() + ", Created at: " + bucket.creationDate()); 
    
            }

        } catch (S3Exception e) {
            System.err.println("Erro do S3: " + e.awsErrorDetails().errorMessage());
            System.err.println("Código HTTP: " + e.statusCode());
        } catch (SdkException e) {
            System.err.println("Erro do SDK AWS: " + e.getMessage());
        }   
    }
    
}
