from faker import Faker
import json
import random
import argparse

class FakeDataGenerator:
    def __init__(self, num_records, null_probability=0):
        self.num_records = num_records
        self.null_probability = null_probability
        self.fake = Faker()

    def generate_record(self):
        """Generates a single record with just username and password."""
        record = {
            "username": self._get_value_or_null("aselsan"),  # Static username as specified
            "password": self._get_value_or_null(self.fake.password())   # Static password as specified
        }
        return record

    def _get_value_or_null(self, value):
        """Randomly returns the value or null based on the null probability."""
        return value if random.random() > self.null_probability else None

    def generate_fake_data(self):
        """Generates multiple records."""
        return [self.generate_record() for _ in range(self.num_records)]

    @staticmethod
    def save_to_json(filename, data):
        """Saves the data to a JSON file."""
        with open(filename, 'w') as file:
            json.dump(data, file, indent=2)

def parse_arguments():
    """Parse command line arguments."""
    parser = argparse.ArgumentParser(description="Generate fake username and password data.")
    parser.add_argument('num_records', type=int, help='Number of fake records to generate')
    parser.add_argument('--null_probability', type=float, default=0, help='Probability of an attribute being null (default: 0.2)')
    parser.add_argument('--output', type=str, default='..src/test/resources/userData.json', help='Output JSON file name (default: deneme.json)')

    return parser.parse_args()

if __name__ == "__main__":
    args = parse_arguments()

    generator = FakeDataGenerator(num_records=args.num_records, null_probability=args.null_probability)
    fake_data = generator.generate_fake_data()
    generator.save_to_json(args.output, fake_data)
    print(f"Data has been saved to '{args.output}'")
