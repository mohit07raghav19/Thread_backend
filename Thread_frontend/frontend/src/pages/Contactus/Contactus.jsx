import Footer from "../../components/Footer";
import UserNavBar from "../../components/UserNavBar";

export default function Contactus() {
  const servicesItems = [
    "Mobile development",
    "UI/UX Design",
    "web development",
    "SEO",
  ];

  return (
    <div>
      {/* <UserNavBar /> */}
      <main className="flex overflow-hidden">
        <div className="flex-1 hidden lg:block">
          <img
            src="https://res.cloudinary.com/floatui/image/upload/v1670701901/scott-webb-NQymDb5XqC4-unsplash_ezrolm.jpg"
            className="w-full h-screen object-cover"
          />
        </div>
        <div className="py-12 flex-1 lg:flex lg:justify-center lg:h-screen lg:overflow-auto">
          <div className="max-w-lg flex-1 mx-auto px-4 text-gray-600">
            <div>
              <h3 className="text-gray-800 text-3xl font-semibold sm:text-4xl">
                Get in touch
              </h3>
              <p className="mt-3">
                We’d love to hear from you! Please fill out the form bellow.
              </p>
            </div>
            <form
              onSubmit={(e) => e.preventDefault()}
              className="space-y-5 mt-12 lg:pb-12"
            >
              <div>
                <label className="font-medium">Full name</label>
                <input
                  type="text"
                  required
                  name="name"
                  className="w-full mt-2 px-3 py-2 text-gray-500 bg-transparent outline-none border focus:border-gray-800 shadow-sm rounded-lg"
                />
              </div>
              <div>
                <label className="font-medium">Email</label>
                <input
                  type="email"
                  required
                  name="email"
                  className="w-full mt-2 px-3 py-2 text-gray-500 bg-transparent outline-none border focus:border-gray-800 shadow-sm rounded-lg"
                />
              </div>
              <div>
                <label className="font-medium">Subject</label>
                <input
                  type="text"
                  required
                  name="subject"
                  className="w-full mt-2 px-3 py-2 text-gray-500 bg-transparent outline-none border focus:border-gray-800 shadow-sm rounded-lg"
                />
              </div>
              <div>
                <label className="font-medium">Message</label>
                <textarea
                  required
                  className="w-full mt-2 h-36 px-3 py-2 resize-none appearance-none bg-transparent outline-none border focus:border-gray-800 shadow-sm rounded-lg"
                  name="message"
                ></textarea>
              </div>
              <button className="w-full px-4 py-2 text-white font-medium bg-gray-800 hover:bg-gray-700 active:bg-gray-900 rounded-lg duration-150">
                Submit
              </button>
            </form>
          </div>
        </div>
      </main>
      <Footer />
    </div>
  );
}
