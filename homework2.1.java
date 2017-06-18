/* 1b - We want our tests to have full statement coverage, which means that
 * s1, s2, ..., s9 have to be executed 
 */

/* This test should enter both if statements and should
 * cover s1, s2, s3, s5, s6, s9.
 * In order for it to enter these statements, a>x and b>y
 * The return value will be 1 + 1 = 2
 * This test covers the path b1,b3
 */
@Test
public void test1() {
    assertEquals(2, coverage(1, 1, 0, 0));
}

/* This test should enter the else then else-if statements and should
 * cover s1, s2, s4, s5, s7, s8, s9
 * In order for it to eter these statements a<=x, b<=y, and
 * value (which should be -1 based on these assumptions) * x <= -a
 * The return value will be -1 - 1 = -2
 * This path covers the path b2,b4,b5
 */
@Test
public void test2() {
	assertEquals(-2, coverage(0, 0, 0, 0));
}

/* 1c - We want our tests to achieve full branch coverage, which means
 * that, in our diagram, b1, ..., b6 should all be executed.
 * Looking at the above tests, we see that the following branches have already
 * been tested: b1, b2, b3, b4, b5.
 * This means that we need to test b6: the case where the second if/else-if staement
 * isn't satisfied. Therefore, we need a test where b<=y and value*x > -a.
 * For this test, we will also use a>x so value will be 1.
 * This path covers the path b1,b4,b6
 */
@Test
public void test3() {
	assertEquals(1, coverage(1, 0, 0, 0));
}

/* 1d - We want our tests to achieve full feasible path coverage. There are a 
 * couple of places where our code branches off. Our code can take b1 or b2.
 * In addition, it can take b3 or [b4,b5] or [b4,b6].
 * This means that all possible paths include:
 * 		1. b1,b3 - covered by test1
 * 		2. b1,b4,b5
 * 		3. b1,b4,b6 - covered by test3
 * 		4. b2,b3
 * 		5. b2,b4,b5 - covered by test2
 * 		6. b2,b4,b6
 * We will then attempt to create tests to cover the remaining paths
 * starting from the top of the list and moving to the bottom.
 */

/* We need a test that covers b1,b4,b5, which means that we need
 * a>x (implies value=1) as well as (value*x <= -a) and b<=y. This path makes
 * value=1 so (value*x) will simplify to just x. The second condition then
 * becomes x<=-a
 */
@Test
public void test4() {
	assertEquals(0, coverage(-1, 0, -2, 1));
}

/* We need atest that covers b2,b3, which means that we need 
 * a<=x and b>y.
 */
@Test
public void test5() {
	assertEquals(0, coverage(0, 1, 1, 0));
}

/* 1d and 1e
 * We need a test that covers b2,b4,b6, which means that we need
 * a<=x, b<=y, (value*x > -a). From these assumptions, we see that
 * value at the point of the second condition will be equal to -1.
 * Therefore the condition will become (-x > -a). Multiplying the
 * inequality by -1 yields (x<a). However, the first condition states 
 * that (x>=a). We see there is no way to fulfill both of these conditions
 * so this is an infeasible path.