import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Train } from '../train';
import { TraindataService } from '../traindata.service';


@Component({
  selector: 'app-addtrains',
  templateUrl: './addtrains.component.html',
  styleUrls: ['./addtrains.component.css']
})
export class AddtrainsComponent implements OnInit {

  train : Train = new Train("", "", "", "", 0, 0, "");
  message:any;

  constructor(private service: TraindataService, private router: Router) { }

  ngOnInit(): void {
  }

  public addTrain() {
    let response = this.service.addNew(this.train);
    response.subscribe((data:any) => {
      this.message = data;
      console.warn("data",data);
      (<any>this.router).navigate(["/managetrains"])  
        alert("Train Added Successfully")
    });
  }
}
