package com.omkar.TaskManagementSystem.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	public String secretKey;
	
	public JwtService() throws NoSuchAlgorithmException {
		
		KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256"); // we are using SHA256 key Generator we can use our own key also
		SecretKey sk = keyGen.generateKey();
		secretKey = Base64.getEncoder().encodeToString(sk.getEncoded()); // we are encoding that key using base64 encoder
		
	}

	public String generateToken(String username) {
		Map<String,Object> claims = new HashMap<>();
		
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+60 *60 *30))
				.and()
				.signWith(getKey())
				.compact();
	}
	
//	Jwts.builder()
//    .setClaims(claims) // Set the claims
//    .setSubject(username) // Set the subject
//    .setIssuedAt(new Date(System.currentTimeMillis())) // Set the issued at time
//    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 30)) // Set the expiration time
//    .signWith(getKey()) // Sign the JWT with a key
//    .compact(); // Build the JWT
//

	private SecretKey getKey() {
		byte[] keyBtyes = Decoders.BASE64.decode(secretKey);
		// TODO Auto-generated method stub
		return Keys.hmacShaKeyFor(keyBtyes);
	}

	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		// TODO Auto-generated method stub
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		
		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}


}
