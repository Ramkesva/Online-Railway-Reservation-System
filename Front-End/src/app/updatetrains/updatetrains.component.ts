import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Train } from '../train';
import { TraindataService } from '../traindata.service';

@Component({
  selector: 'app-updatetrains',
  templateUrl: './updatetrains.component.html',
  styleUrls: ['./updatetrains.component.css']
})
export class UpdatetrainsComponent implements OnInit {

  train : Train = new Train("", "", "", "", 0, 0, "");
  message:any;

  constructor(private service: TraindataService, private router: Router) { }

  ngOnInit(): void {
  }

  public updateTrain() {
    let response = this.service.addNew(this.train);
    response.subscribe((data:any) => {
      this.message = data;
      console.warn("data",data);
      (<any>this.router).navigate(["/managetrains"])  
        alert("Train Updated Successfully")
    });
  }

}
