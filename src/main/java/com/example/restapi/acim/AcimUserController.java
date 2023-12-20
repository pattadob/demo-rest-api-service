package com.example.restapi.acim;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/acimuser")
public class AcimUserController {

    private final EcmUserService ecmUserService;

    public AcimUserController(EcmUserService ecmUserService){
        this.ecmUserService = ecmUserService;
    }

    @GetMapping("")
    public List<AcimUser> findAll(){
        return ecmUserService.findAll();
    }

    @PostMapping("")
    public AcimResponse create(@RequestBody List<AcimUser> users) {
        List<String> failList = ecmUserService.createUser(users);
        return getResponse(failList);
    }

    @PutMapping("")
    public AcimResponse update(@RequestBody List<AcimUser> users) {
        List<String> updateFails = ecmUserService.updateUser(users);
        return getResponse(updateFails);
    }

    @PostMapping("/del")
    public AcimResponse delete(@RequestBody List<AcimUser> users) {
        List<String> deleteFails = ecmUserService.deleteUser(users);
        return getResponse(deleteFails);
    }
    
    AcimResponse getResponse(List<String> failList) {
        String code = failList.isEmpty() ? "20000" : "20001";
        String desc = failList.isEmpty() ? "Success" : "Some Process Fail";
        List<AcimResponseDetail> fails = failList.isEmpty() ? null
                : failList.stream()
                        .map(AcimResponseDetail::new)
                        .collect(Collectors.toList());
        return new AcimResponse(code, desc, fails);
    }
}
