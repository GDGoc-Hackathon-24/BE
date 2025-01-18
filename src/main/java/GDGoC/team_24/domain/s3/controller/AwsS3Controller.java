//package GDGoC.team_24.domain.s3.controller;
//
//import GDGoC.team_24.domain.s3.service.AwsS3Service;
//import GDGoC.team_24.global.ApiResponse;
//import GDGoC.team_24.global.code.status.SuccessStatus;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("api/s3")
//public class AwsS3Controller {
//
//    private final AwsS3Service awsS3Service;
//    @PostMapping(consumes = "multipart/form-data")
//    public ApiResponse<List<String>> s3upload(@RequestPart(required = false) List<MultipartFile> imgs) {
//        List<String> img_urls = new ArrayList<>();
//        for(MultipartFile img : imgs){
//            img_urls.add(awsS3Service.uploadFile(img));
//        }
//        return ApiResponse.of(SuccessStatus._OK,img_urls);
//    }
//}
//
//
//
