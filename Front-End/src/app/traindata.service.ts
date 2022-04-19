import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

// export class Trains {
//   constructor(
//    public  trainNo: String,
// 	  public  trainName: String,
// 	  public  trainFrom: String,
// 	  public  trainTo: String,
// 	  public  fare: number,
// 	  public  seats: number,
// 	  public  time: String
//   ) {
//   }
// }

@Injectable({
  providedIn: 'root'
})
export class TraindataService {

  constructor(private http:HttpClient) { }

  public getTrains() {
    return this.http.get("http://localhost:8087/train/viewalltrains");
  }

  public deleteTrain(trainNo:any){
    return this.http.delete("http://localhost:8087/train/deletetrain/"+trainNo);
  }

  public addNew(train:any) {
    return this.http.post("http://localhost:8087/train/addtrain", train, { responseType: "text" as "json" });
  }

  getTrainsbyfrom(trainFrom:any) {
    return this.http.get("http://localhost:8087/train/findfrom/"+trainFrom);
  }

  getTrainsbyto(trainTo:any) {
    return this.http.get("http://localhost:8087/train/findto/"+trainTo);
  }

  getTrainsbw(fromTrain:any,toTrain:any) {
    return this.http.get("http://localhost:8087/train/findbw/"+fromTrain+'/'+toTrain);

  }

}