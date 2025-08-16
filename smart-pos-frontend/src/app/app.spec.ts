
describe('Simple math tests', () => {
  it('should add numbers correctly', () => {
    expect(1 + 2).toBe(3);
  });

  it('should multiply numbers correctly', () => {
    expect(2 * 5).toBe(10);
  });

  it('should compare values correctly', () => {
    expect(10).toBeGreaterThan(5);
    expect(5).toBeLessThan(10);
  });
});
