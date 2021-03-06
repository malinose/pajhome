import java.math.BigInteger;
import java.security.SecureRandom;

class Rsa
{
  private BigInteger n, d, e;

  public Rsa(int bitlen)
  {
    SecureRandom r = new SecureRandom();
    BigInteger p = new BigInteger(bitlen / 2, 100, r);
    BigInteger q = new BigInteger(bitlen / 2, 100, r);
    n = p.multiply(q);
    BigInteger m = (p.subtract(BigInteger.ONE))
                   .multiply(q.subtract(BigInteger.ONE));
    e = new BigInteger("3");
    while(m.gcd(e).intValue() > 1) e = e.add(new BigInteger("2"));
    d = e.modInverse(m);
  }
  public BigInteger encrypt(BigInteger message)
  {
    return message.modPow(e, n);
  }
  public BigInteger decrypt(BigInteger message)
  {
    return message.modPow(d, n);
  }
}
