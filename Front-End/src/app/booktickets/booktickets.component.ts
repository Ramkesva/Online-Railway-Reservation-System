import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Booking } from '../booking';
import { Train } from '../train';
import { TraindataService } from '../traindata.service';

@Component({
  selector: 'app-booktickets',
  templateUrl: './booktickets.component.html',
  styleUrls: ['./booktickets.component.css']
})
export class BookticketsComponent implements OnInit {

  trainNo!: String;
  train: Train = new Train();
  book: Booking = new Booking();
  message:any;

  //constructor(private service: TraindataService, private router: Router) { }
  constructor(private service: TraindataService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.trainNo = this.route.snapshot.params['trainNo'];
    this.service.getTrainByNo(this.trainNo).subscribe( data => {
      this.train = data;
      console.log(this.train);
    }, error => console.log(error));
  }

  bookTicket() {
    this.service.updateTrains(this.trainNo, this.train).subscribe( data => {
      this.goToUserHome();
    }, error => console.log(error));
  }

  goToUserHome() {
    this.router.navigate(['/userhome']);
    alert("Ticket Booking Successful")
  }

}
