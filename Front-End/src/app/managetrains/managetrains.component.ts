import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TraindataService } from '../traindata.service';
//import { TestObject } from 'protractor/built/driverProviders';

@Component({
  selector: 'app-managetrains',
  templateUrl: './managetrains.component.html',
  styleUrls: ['./managetrains.component.css']
})
export class ManagetrainsComponent implements OnInit {

  trains : any;

  constructor(private service: TraindataService, private router: Router) { }

  ngOnInit() {
    let response = this.service.getTrains();
    response.subscribe((data:any) => this.trains = data);
  }

  updateTrain(trainNo:number){
    console.log(trainNo);
    this.router.navigate(["updatetrains",trainNo]);
    
  }

  public deleteTrains(trainNo : number){
    let response = this.service.deleteTrain(trainNo);
    response.subscribe((data:any) => this.trains=data);
      (<any>this.router).navigate(["/managetrains"])  
        alert("Train deleted Successfully")
  }

}

