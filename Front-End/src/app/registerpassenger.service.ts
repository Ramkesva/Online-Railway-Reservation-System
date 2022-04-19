import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

export class userlogin {
  constructor(
    public id: String,
    public  username: String,
    public  password: String
  ) {
  }
}

@Injectable({
  providedIn: 'root'
})
export class RegisterpassengerService {

  constructor(private http: HttpClient) {
  }
  saveResto(data: any)
   {
      return this.http.post<userlogin[]>('http://localhost:8089/user/register',data)
   }
  }
