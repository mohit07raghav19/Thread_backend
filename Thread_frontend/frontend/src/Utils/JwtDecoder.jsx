import jwt_decode from "jwt-decode";

export const JwtDecoder = (token) => {
    var decoded = jwt_decode(token);
 
console.log(decoded);
  return decoded;
}
