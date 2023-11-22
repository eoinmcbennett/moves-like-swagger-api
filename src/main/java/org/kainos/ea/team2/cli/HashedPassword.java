package org.kainos.ea.team2.cli;

/**
 * Describes a hashed password.
 */
public class HashedPassword {
    /**
     * Number of iterations used in the hashing algorithm.
     */
    private int iterations;

    /**
     * The hash of the password.
     */
    private byte[] hashedPassword;

    /**
     * The salt used with hashing.
     */
    private byte[] salt;

    /**
     * Creates a new hashed password instance.
     * @param hashedPassword the hash that was generated
     * @param salt the salt used to generate it
     * @param iterations the number of iterations used to generate it
     */
    public HashedPassword(
            final byte[] hashedPassword,
            final byte[] salt,
            final int iterations) {
        this.iterations = iterations;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    /**
     * Get the iterations used for hashing.
     * @return number of iterations.
     */
    public int getIterations() {
        return iterations;
    }

    /**
     * Gets the hashed password.
     * @return byte[] describing hashed password.
     */
    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Gets the salt for this hash.
     * @return byte[] describing salt
     */
    public byte[] getSalt() {
        return salt;
    }
}
