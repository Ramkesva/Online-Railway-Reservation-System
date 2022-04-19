import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginPassengerService } from '../loginpassenger.service';

@Component({
  selector: 'app-loginuser',
  templateUrl: './loginuser.component.html',
  styleUrls: ['./loginuser.component.css']
})
export class LoginuserComponent implements OnInit {
  x: any;

  adminlog=new FormGroup(
    {
        username: new FormControl('',Validators.required),
        password: new FormControl('',Validators.required),
        
    })
    constructor(private httpClientService: LoginPassengerService, private router:Router) { }
  
    ngOnInit(): void {
    }
    validate()
    {
      //console.warn(this.addResto.value)
      this.httpClientService.saveResto(this.adminlog.value).subscribe((result: any)=>{
      console.warn("result",result)    
        this.x=result
        console.log(this.x)
        console.warn(this.x.jwt)
        if(this.x.jwt=="no"){
          (<any>this.router).navigate(["/userLogin"])  
          alert("Invalid Credentials");
        }
        else
        {
          (<any>this.router).navigate(["/userhome"])
          alert("Welcome!");

        }
    })
    }
  } 