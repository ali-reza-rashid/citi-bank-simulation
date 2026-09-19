import pandas as pd
import matplotlib.pyplot as plt

# Paste your Java output between the triple quotes below
java_output = """
515.85999
2026-09-19T19:03:00.588035061Z
515.85999
2026-09-19T19:03:16.559346366Z
515.85999
2026-09-19T19:03:31.803696476Z
515.85999
2026-09-19T19:03:47.044361416Z
515.85999
2026-09-19T19:04:02.415576935Z
515.85999
2026-09-19T19:04:17.664007517Z
515.85999
2026-09-19T19:04:32.945288722Z
515.85999
2026-09-19T19:04:48.207313559Z
515.85999
2026-09-19T19:05:03.437314240Z
515.85999
2026-09-19T19:05:18.677589879Z
515.85999
2026-09-19T19:05:33.945115378Z
"""

# Clean the string and split into a list of lines
lines = [line.strip() for line in java_output.strip().split('\n')]

# Extract alternating lines: evens are prices, odds are timestamps
prices = lines[0::2]
timestamps = lines[1::2]

# Convert to DataFrame
df = pd.DataFrame({"price": prices, "timestamp": timestamps})

# Convert data types
df["price"] = df["price"].astype(float)
df["timestamp"] = pd.to_datetime(df["timestamp"])

print(df)

# Plot the data
plt.figure(figsize=(10, 5))
# marker="o" makes the points visible
plt.plot(df["timestamp"], df["price"], marker="o", linestyle="-")

plt.title("DIA Price Over Time")
plt.xlabel("Timestamp")
plt.ylabel("Price")

plt.xticks(rotation=45)
plt.tight_layout()
plt.show()
